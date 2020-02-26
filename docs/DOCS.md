# Documentation

## Print to screen
| Return Type | Function |
| ------| -----------|
|`void`|`print(Object obj)`<br> Prints any object to the screen.|
|`void`|`println(Object obj)`<br> Prints any object to the screen then goes to new line.|

## Read Input
| Return Type | Function |
| ------| -----------|
|`String`|`readString()`<br> `readString(String text)`<br> `text` : optional text to display on the input frame.<br> Returns the String value of a keybord entry.|
|`Double`|`readDouble()`<br> `readDouble(String text)`<br> `text` : optional text to display on the input frame.<br> Returns the Double value of a keybord entry.|
|`Integer`|`readInt()`<br>`readInteger()`<br> `readInt(String text)`<br> `readInteger(String text)`<br>  `text` : optional text to display on the input frame.<br> Returns the Integer value of a keybord entry.|
|`Long`|`readLong()`<br> `readLong(String text)`<br> `text` : optional text to display on the input frame.<br> Returns the Long value of a keybord entry.|
|`Float`|`readFloat()`<br> `readFloat(String text)`<br> `text` : optional text to display on the input frame.<br> Returns the Float value of a keybord entry.|
|`Boolean`|`readBoolean()`<br> `readBoolean(String text)`<br> `text` : optional text to display on the input frame.<br> Returns the Boolean value of a keybord entry.|
|`Char`|`readChar()`<br> `readCharacter()`<br> `readChar(String text)`<br> `readCharacter(String text)`<br> `text` : optional text to display on the input frame.<br> Returns the Char value of a keybord entry.|

## Utils
| Return Type | Function |
| ------| -----------|
|`void`|`sleep(int sleepingTime)`<br> Stops the execution of the program during `sleepingTime` period in milliseconds.|
|`int`|`random(int min, int max)`<br> Returns a random value between `min` included and `max` excluded.|
|`boolean`|`equal(String s1, String s2)`<br> Returns `true` if the value of `s1` is exactly the same as `s2`, returns `false` if not.|

## Pixels
<img src="img/drawer-axes.png" alt="upec" width="400"/>

| Return Type | Function |
| ------| -----------|
|`void`|`reset(int w, int h)`<br> Creates a drawing window of width=`w` and height=`h`.|
|`int`|`getWidth()`<br> Returns the width value of the drawing window.|
|`int`|`getHeight()`<br> Returns the height value of the drawing window.|
|`boolean`|`setPixel(int x, int y, String color)`<br> Draws a point with a defined `color` at the coordinates (`x`, `y`).<br> `color`: a color string that can be one of [black, blue, cyan, grey, green, magenta, orange, red, white, yellow, pink]<br> Returns `true` if the point is inside of the boundaries of the drawing window, returns `false` if not.|
|`boolean`|`setPixel(int x, int y, int alpha)`<br> Draws a point with a defined shade of grey at the coordinates (`x`, `y`).<br> `alpha`: defines the shade of grey value ( min = 0 for black; max = 255 for white).<br> Returns `true` if the point is inside of the boundaries of the drawing window, returns `false` if not.|
|`boolean`|`setPixel(int x, int y, int r, int g, int b) `<br> Draws a point with a defined rgb color at the coordinates (`x`, `y`).<br> `r`: Red value between 0 and 255.<br> `g`: Green value between 0 and 255.<br> `b`: Blue value between 0 and 255.<br> Returns `true` if the point is inside of the boundaries of the drawing window, returns `false` if not.|

