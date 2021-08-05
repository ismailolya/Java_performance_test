package navigationtiming;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.openqa.selenium.JavascriptExecutor;
import util.WebDriverHolder;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static util.Constants.DATABASE_NAME;
import static util.Constants.*;

public class PerfNavigationTiming {

    Map<String, Object> timings = null;

    private final String javaScriptForPerformance = "var performance = window.performance || window.webkitPerformance ||" +
            " window.mozPerformance || window.msPerformance || {}; var timings = performance.timing || {};return timings;";


    //private final String javaScriptForPerformance = "var timings = performance.timing || {}; return timings;";

    InfluxDB influxDB = InfluxDBFactory.connect(DATABASE_URL);

    BatchPoints batchPoints = BatchPoints
            .database(DATABASE_NAME)
            .retentionPolicy("autogen")
            .build();

    public void writeToInflux(String pageName){
        getAllTiming();

        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        Point point = Point.measurement("clientSide")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("projectName", PROJECT_NAME)
                .tag("scenario", SCENARIO_NAME)
                .tag("env", ENV_NAME)
                .tag("browser", BROWSER_NAME)
                .tag("page", pageName)
                .tag("periodicity", "Debug")
                .tag("periodicity_comment", "nopcommerce")
                .tag("buildID", BUILD_ID)
                .addField("latency", this.getLatency())
                .addField("backendResponse", this.getBackEndResponse())
                .addField("tti", this.getTimeToInteract())
                .addField("ttl", this.getTimeToLoad())
                .addField("onload", this.getOnLoad())
                .addField("total_time", this.getTotalTime())
                .build();
        batchPoints.point(point);
        influxDB.write(batchPoints);
    }

    public Map<String, Object> getAllTiming(){
        JavascriptExecutor jsrunner = (JavascriptExecutor) WebDriverHolder.getDriver();
        timings = (Map<String, Object>) jsrunner.executeScript(javaScriptForPerformance);
        return timings;
    }

    private Long getAnTime(String name){return (Long) timings.get((Object) name);}

    public Long getNavigationStart(){return getAnTime("navigationStart");}

    public Long getResponseStart(){return getAnTime("responseStart");}

    public Long getResponseEnd(){return getAnTime("responseEnd");}

    public Long getDomLoading(){return getAnTime("domLoading");}

    public Long getDomInteractive(){return getAnTime("domInteractive");}

    public Long getDomComplete(){return getAnTime("domComplete");}

    public Long getLoadEventStart(){return getAnTime("loadEventStart");}

    public Long getLoadEventEnd(){return getAnTime("loadEventEnd");}

    //results

    public Long getLatency(){return getResponseStart() - getNavigationStart();}

    public Long getBackEndResponse(){return getResponseEnd() - getResponseStart();}

    public Long getTimeToInteract(){return getDomInteractive() - getDomLoading();}

    public Long getTimeToLoad(){return getDomComplete() - getDomInteractive();}

    public Long getOnLoad(){return getLoadEventEnd() - getLoadEventStart();}

    public Long getTotalTime(){return getLoadEventEnd() - getNavigationStart();}

}
