# AndroidWear
Android Wear app that features all the basic components from wearable support library.

This repository is a detailed example of building apps for android wear devices which are these days making a mark in the gadget 
market. To get started [Developing Android wear Apps: Android wear Fundamentals](http://developer.android.com/training/building-wearables.html)

#Installation
Clone the repository and check the repository from oldest commits.

#WearableListView
An alternative version of ListView that is optimized for ease of use on small screen wearable devices. 
It displays a vertically scrollable list of items, and automatically snaps to the nearest item when the user stops scrolling.
[Google Developer Documentaion](http://developer.android.com/reference/android/support/wearable/view/WearableListView.html).
With customlayout and custom adapter this example triggers a toast whenever user taps on the listitem.

![Screen_after_response](https://github.com/ashokslsk/AndroidWear/blob/master/screens/Screen_1.png)
![Screen_after_toast](https://github.com/ashokslsk/AndroidWear/blob/master/screens/Screen_2.png)

#DelayedConfirmationView 
DelayedConfirmationView provides a circular countdown timer, typically used to automatically confirm an operation after a short delay has elapsed.
The delay is intended to give the user a chance to cancel the operation by tapping the View.
[Google Developer Documentation](http://developer.android.com/reference/android/support/wearable/view/DelayedConfirmationView.html)

![Screen_after_response](https://github.com/ashokslsk/AndroidWear/blob/master/screens/Screen_3.png)
![Screen_after_toast](https://github.com/ashokslsk/AndroidWear/blob/master/screens/Screen_4.png)

#GridViewPager
Layout manager that allows the user to navigate both vertically and horizontally through pages of content. 
You supply an implementation of a GridPagerAdapter to generate pages for the view to show
[Google Developer Documentaion](http://developer.android.com/reference/android/support/wearable/view/GridViewPager.html)
![Screen_after_response](https://github.com/ashokslsk/AndroidWear/blob/master/screens/Screen_5.png)
![Screen_after_toast](https://github.com/ashokslsk/AndroidWear/blob/master/screens/Screen_6.png)
![Screen_after_response](https://github.com/ashokslsk/AndroidWear/blob/master/screens/Screen_7.png)
![Screen_after_toast](https://github.com/ashokslsk/AndroidWear/blob/master/screens/Screen_8.png)

#Wearable Activity
Base activity class for use on wearables. Provides compatibility for Ambient mode support.

Some Wear apps are most useful when they are constantly visible to the user. For example, 
users out on a run can glance at their wearable to see the distance covered and time elapsed, 
or after recording a grocery list on their wearable, users can quickly see which items are remaining on the list as they shop at the market. 
Making an app constantly visible has an impact on battery life, 
so you should carefully consider that impact when adding this feature to your app.
[Google Developer Documentation](http://developer.android.com/reference/android/support/wearable/activity/WearableActivity.html).

![Screen_after_toast](https://github.com/ashokslsk/AndroidWear/blob/master/screens/Screen_9.png)

#Basic and MultiPage Notifications
When you'd like to provide more information without requiring users to open your app on their handheld device,
you can add one or more pages to the notification on the wearable. 
The additional pages appear immediately to the right of the main notification card.

![Screen_after_toast](https://github.com/ashokslsk/AndroidWear/blob/master/screens/Screen_10.png)
![Screen_after_toast](https://github.com/ashokslsk/AndroidWear/blob/master/screens/Screen_11.png)

#Stacked and Action Button Notifications
When creating notifications for a handheld device, you should always aggregate similar notifications into a single summary notification. 
For example, if your app creates notifications for received messages, you should not show more than one notification on a handheld device—when
more than one is message is received, use a single notification to provide a summary such as "2 new messages."
[Google Developer Documentation](http://developer.android.com/training/wearables/notifications/stacks.html)

![Screen_after_toast](https://github.com/ashokslsk/AndroidWear/blob/master/screens/Screen_12.png)
![Screen_after_toast](https://github.com/ashokslsk/AndroidWear/blob/master/screens/Screen_13.png)

#Voice Reply Notifications
If you have handheld notifications that include an action to input text, such as reply to an email, 
it should normally launch an activity on the handheld device to input the text. 
However, when your notification appears on a wearable, there is no keyboard input, so you can let users dictate a reply or provide pre-defined text messages using RemoteInput.
[Google Developer Documentation](http://developer.android.com/training/wearables/notifications/voice-input.html)

![Screen_after_toast](https://github.com/ashokslsk/AndroidWear/blob/master/screens/Screen_15.png)

#Custom Notifications
Creating layouts for wearables is the same as handheld devices,
except you have to design for the screen size and for glanceability. 
Do not port functionality and the UI from a handheld app and expect a good experience.
You should create custom layouts only when necessary. 
Read the design guidelines for information on how to design great wearable apps.
[Google Developer Documentation](http://developer.android.com/training/wearables/apps/layouts.html)

![Screen_after_toast](https://github.com/ashokslsk/AndroidWear/blob/master/screens/Screen_16.png)

#Android Wear WatchFace
Watch faces in Android Wear leverage a dynamic digital canvas to tell time using colors, animations, and relevant
contextual information. The Android Wear companion app provides watch faces with different styles and shapes.
When users select one of the available watch faces on the wearable or on the companion app, the wearable device 
previews the watch face and lets the user set configuration options.
Android Wear enables you to create custom watch faces for Wear devices. When users install a handheld app that contains
a wearable app with watch faces, they become available in the Android Wear companion app on the handheld device and in the
watch face picker on the wearable device.
This class teaches you to implement custom watch faces and to package them inside a wearable app. This class also covers design considerations and implementation tips to ensure that your designs integrate with system UI elements and are power-efficient.
[Google Developer Documentation](http://developer.android.com/training/wearables/watch-faces/index.html)

![Screen_after_toast](https://github.com/ashokslsk/AndroidWear/blob/master/screens/Screen_17.png)


## License
```
MIT License

Copyright (c) 2016 Ashok Kumar S

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```












