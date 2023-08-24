package ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Input;

public interface IDurationInput {

    String getDuration();
    int parseDuration();
    int tryParseDuration();
    void setDuration(int duration);
    void setDurationError(String error);
}
