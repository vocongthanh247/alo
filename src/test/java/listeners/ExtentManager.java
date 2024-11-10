package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    public static ExtentReports extent;
    //    public static ExtentTest test;
    private static String reportBaseDirectory;

    public static void setExtentVPA() {
        if (extent == null) {
            createReportDirectory("VPA");
            extent = createExtentReport("VPA Android Test Report");
        }
    }

    public static void setExtentVneCSGT() {
        if (extent == null) {
            createReportDirectory("VneCSGT");
            extent = createExtentReport("VneCSGT Android Test Report");
        }
    }

    private static void createReportDirectory(String projectName) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportBaseDirectory = System.getProperty("user.dir") + "/test-output/" + projectName + "/" + timestamp;
        new File(reportBaseDirectory + "/screenshots").mkdirs();
    }

    private static ExtentReports createExtentReport(String reportName) {
        ExtentReports extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportBaseDirectory + "/TestReport.html")
                .viewConfigurer()
                .viewOrder()
                .as(new ViewName[] {
                        ViewName.DASHBOARD,
                        ViewName.TEST,
                        ViewName.CATEGORY,
                        ViewName.AUTHOR,
                        ViewName.DEVICE,
                        ViewName.LOG
                })
                .apply();

        // Configure report appearance
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Test Execution Report");
        sparkReporter.config().setReportName(reportName);
        sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        sparkReporter.config().setEncoding("UTF-8");
        sparkReporter.config().setJs(getCustomJS());
        sparkReporter.config().setCss(getCustomCSS());

        extentReports.attachReporter(sparkReporter);

        // Add system info
        extentReports.setSystemInfo("Project", reportName);
        extentReports.setSystemInfo("Environment", "Android");
        extentReports.setSystemInfo("Platform", "Mobile");
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));

        return extentReports;
    }

    private static String getCustomJS() {
        return "$(document).ready(function() { " +
                "    $('.test-filter').on('change', function() { " +
                "        var status = $(this).val(); " +
                "        $('.test-case').hide(); " +
                "        $('.test-case.' + status).show(); " +
                "    }); " +
                "    $('.test-step').each(function() { " +
                "        var time = new Date().toLocaleTimeString(); " +
                "        $(this).prepend('<span class=\"step-time\">[' + time + '] </span>'); " +
                "    }); " +
                "});";
    }

    private static String getCustomCSS() {
        return ".report-name { padding-left: 10px; margin-bottom: 10px; color: #ffffff; } " +
                ".report-name img { float: left; height: 40px; margin-right: 10px; margin-top: 5px; } " +
                ".test-details { padding: 5px; margin: 5px; border: 1px solid #ccc; } " +
                ".test-status.pass { color: #2ecc71; } " +
                ".test-status.fail { color: #e74c3c; } " +
                ".test-steps { margin-left: 20px; } " +
                ".badge-primary { background-color: #007bff; } " +
                ".badge-success { background-color: #28a745; } " +
                ".badge-danger { background-color: #dc3545; } " +
                ".badge-warning { background-color: #ffc107; } " +
                ".badge-info { background-color: #17a2b8; }";
    }

    public static void endReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static String getReportBaseDirectory() {
        return reportBaseDirectory;
    }
}