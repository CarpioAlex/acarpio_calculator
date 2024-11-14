# acarpio calculator

**Video:**
https://github.com/user-attachments/assets/5ab6a2c5-91b4-4e6a-be71-a3b834a78324



## Extras:

Since this applicaiton had a lot of buttons, I used View bindings to not use view.FindViewByID(...) to make the retreiving of the views easier.

It works in both landscape and portrait.

To solve the problem of the default material theme, I used a lighter material: 

     <style name="Base.Theme.Acarpio_calculator" parent="Theme.AppCompat.DayNight.NoActionBar">

Along with that, I added a loop that assigns the drawables to the button.

A switch switches between the normal and the "ugly" mode, and to assign the listener.

#### Selectors

To apply a visual effect on disabled buttons and a discoloration when they are not enabled, I added some selectors to the XML resources.

    
    <selector xmlns:android="http://schemas.android.com/apk/res/android">
        
        <item android:state_pressed="true" android:drawable="@drawable/gradiente_pressed" />
    
    
        <item android:drawable="@drawable/gradiente_boton" />
    </selector>
    

#### Drawables and listeners
```java
public void assignListeners(ActivityMainBinding binding, CalculatorListener listener) {
        for (int i = 0; i < binding.gridCalc.getChildCount(); i++) {
            View child = binding.gridCalc.getChildAt(i);
            if (child instanceof Button) {
                child.setOnClickListener(listener);

            }
        }
        for (int i = 0; i < binding.secondGrid.getChildCount(); i++) {
            View child = binding.secondGrid.getChildAt(i);
            if (child instanceof Button) {
                child.setOnClickListener(listener);

            }
        }

        binding.currentHistory.setOnClickListener(listener);


    }

    public static void uglyMode (ActivityMainBinding binding, Context c) {
        for (int i = 0; i < binding.gridCalc.getChildCount(); i++) {
            View child = binding.gridCalc.getChildAt(i);
            if (child instanceof Button) {
                child.setBackground(AppCompatResources.getDrawable(c, R.drawable.selector_boton));
            }
        }
        for (int i = 0; i < binding.secondGrid.getChildCount(); i++) {
            View child = binding.secondGrid.getChildAt(i);
            if (child instanceof Button) {
                child.setBackground(AppCompatResources.getDrawable(c, R.drawable.selector_memoria));
            }
        }

        binding.equals.setBackground(AppCompatResources.getDrawable(c, R.drawable.button_shape));
    }


    public static void niceMode(ActivityMainBinding binding, Context c) {
        for (int i = 0; i < binding.gridCalc.getChildCount(); i++) {
            View child = binding.gridCalc.getChildAt(i);
            if (child instanceof Button) {
                child.setBackground(AppCompatResources.getDrawable(c, android.R.drawable.btn_default));
            }
        }
        for (int i = 0; i < binding.secondGrid.getChildCount(); i++) {
            View child = binding.secondGrid.getChildAt(i);
            if (child instanceof Button) {
                child.setBackground(AppCompatResources.getDrawable(c, android.R.drawable.btn_default));
            }
        }

        binding.equals.setBackground(AppCompatResources.getDrawable(c, android.R.drawable.btn_default));
    }
```









