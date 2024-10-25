package pojo;

public class GetCourse {

    private String url;
    private String services;
    private String expertise;
    private Courses Courses;
    private String instructor;
    private String linkedIn;

    public String getUrl() {
        return url;
    }

    public String getServices() {
        return services;
    }

    public String getExpertise() {
        return expertise;
    }

    public pojo.Courses getCourses() {
        return Courses;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public void setCourses(pojo.Courses courses) {
        Courses = courses;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }
}