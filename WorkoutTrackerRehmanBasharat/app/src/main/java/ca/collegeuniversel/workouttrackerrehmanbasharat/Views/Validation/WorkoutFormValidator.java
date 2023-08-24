package ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Validation;

import android.content.Context;

import java.util.Objects;
import ca.collegeuniversel.workouttrackerrehmanbasharat.R;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Add.AddWorkoutForm;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Edit.EditWorkoutForm;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Input.IDurationInput;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Input.INameInput;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Input.IQuantityInput;

public class WorkoutFormValidator {

    private final Context context;

    public WorkoutFormValidator(Context context) {
        this.context = Objects.requireNonNull(context);
    }

    public boolean validateAddWorkoutForm(AddWorkoutForm form) {
        return validateName(form) & validateDuration(form);
    }

    public boolean validateEditWorkoutForm(EditWorkoutForm form) {
        return validateName(form) & validateDuration(form) & validateQuantity(form);
    }

    private boolean validateName(INameInput form) {
        if (form.getName().isEmpty()) {
            form.setNameError(context.getString(R.string.enter_a_name));
            return false;
        }

        return true;
    }

    private boolean validateDuration(IDurationInput form) {
        if (form.tryParseDuration() < 0) {
            form.setDurationError(context.getString(R.string.enter_a_valid_number_of_duration));
            return false;
        }

        return true;
    }

    private boolean validateQuantity(IQuantityInput form) {
        if (form.tryParseQuantity() < 0) {
            form.setQuantityError(context.getString(R.string.enter_a_valid_quantity));
            return false;
        }

        return true;
    }
}
