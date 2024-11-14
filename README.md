# acarpio calculator

**Video:**

     

<video src="https://github.com/user-attachments/assets/a14346d8-6564-46e6-b11d-08b160d4805c" controls width="600"></video>



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









