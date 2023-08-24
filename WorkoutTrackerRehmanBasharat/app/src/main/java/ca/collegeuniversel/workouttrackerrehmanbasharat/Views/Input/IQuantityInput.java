package ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Input;

public interface IQuantityInput {
    String getQuantity();
    int parseQuantity();
    int tryParseQuantity();
    void setQuantity(int quantity);
    void setQuantityError(String error);
}
