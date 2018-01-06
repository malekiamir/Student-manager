package manager.student;
/*
    Amir Maleki
*/
public class Student {
    String stId;
    String name;
    String familyName;
    double score;
    int passed;
    String trend;

    public Student(String stId, String name, String familyName, double score
            , int passed, String trend) {
        this.stId = stId;
        this.name = name;
        this.familyName = familyName;
        this.score = score;
        this.passed = passed;
        this.trend = trend;
    }
    
    @Override
    public String toString() {
        return "{" + "\"stId\":\"" + stId + "\",\"name\":\"" + name + "\",\"familyName\":\"" + familyName + "\",\"score\":\"" + score + "\",\"passed\":\"" + passed + "\",\"trend\":\"" + trend+ "\"" + '}';
    }
    
    public String toStringP() {
        return name + " " + familyName + " " + " به شماره دانشجویی" + stId ;
    }

}
