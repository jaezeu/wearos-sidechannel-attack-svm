# wearos-sidechannel-attack-svm

## Project Summary

This repository contains the files and source code as part of my Final year project in NTU in 2018. The aim of this project is to conduct a sidechannel attack to find out what are the keys a user is pressing on his/her phone using the movement of the person's wrist IF they are wearing a smartwatch. This project was exclusively only tested on a numeric keypad of a iPhone 7 plus. Therefore, results may vary for other phone models.  

## How it works

For a start, built a WearOS application on Android Studio for the purpose of collecting GyroMeter and Accelerometer readings. This application is then installed on the target smartwatch. For the purpose of this project, a TicWatch E Series watch was used. The test subject then presses a particular key (e.g. '5'), the data is then collected and then labelled in a CSV file. The test was performed on the below two use cases:

1. Wearing smartwatch on right hand and pressing on smartphone keys using right hand
2. Wearing smartwatch on lrft hand and pressing on smartphone keys using left hand

The data is then compiled and then fed into a Support Vector Machine Model(refer to main.py for model details).


## File/Folder Structure in GIT

| File / Folder Name | Usage / Purpose |
| --- | --- |
| acc/ | This folder contains the manifest files to run the wearOS application on Android Studio. Simply clone this repo, launch android studio -> open project -> select "acc" and the project will open |
| main.py | The source code of the SVM model(includes training and testing data as well as hyperparameter tuning using GridCV) |
| dataset/totalrightcomb.csv | Contains all the aggregated data of the use case 1. mentioned above |

