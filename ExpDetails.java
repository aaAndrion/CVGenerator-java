import java.util.LinkedList;
import java.util.List;

public class ExpDetails {
    private String jobTitle, city, employer, startMoYe, startMonth, startYear, endMoYe, endMonth, endYear;
    private List<String> desc;

    public ExpDetails(String jobTitle, String city, String employer, String startMonth, String startYear, String endMonth,
            String endYear, List<String> desc) {
        this.jobTitle = jobTitle;
        this.city = city;
        this.employer = employer;
        this.startMoYe = startMonth + " " + startYear;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.endMoYe = endMonth + " " + endYear;
        this.endMonth = endMonth;
        this.endYear = endYear;
        this.desc = desc;

    }

    public String getJobTitle() { return jobTitle; }

    public String getCityTown() { return city; }

    public String getEmployer() { return employer; }

    public String getStartMoYe() { return startMoYe; }

    public String getStartMonth() { return startMonth; }

    public String getStartYear() { return startYear; }

    public String getEndMoye() { return endMoYe; }

    public String getEndMonth() { return endMonth; }

    public String getEndYear() { return endYear; }

    public List<String> getDesc() {
        List<String> lines = new LinkedList<>();
        for (String line : desc)
            lines.add(line);
        return lines;

    }
}