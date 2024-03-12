import java.util.LinkedList;
import java.util.List;

public class EduDetails {
    private String degree, city, school, startMoYe, startMonth, startYear, endMoYe, endMonth, endYear;   
    private List<String> desc;

    public EduDetails(String degree, String city, String school, String startMonth, String startYear, String endMonth,
            String endYear, List<String> desc) {
        this.degree = degree;
        this.city = city;
        this.school = school;
        this.startMoYe = startMonth + " " + startYear;
        this.startMonth = startMonth;
        this.startYear = startYear;       
        this.endMoYe = endMonth + " " + endYear;
        this.endMonth = endMonth;
        this.endYear = endYear;
        this.desc = desc;
    }

    public String getDegree() { return degree; }

    public String getCityTown() { return city; }

    public String getSchool() { return school; }

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