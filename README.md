# IntegratedML-IRIS-PlatformEntryPrediction


# Prediction of server configuration for entry

The platform server entry prediction can be made based on the hospital's outpatient volume,
service quantity, message quantity, and message storage time before deploying the integrated platform in the hospital.
The required server configuration can be predicted

# Analog data
Platform server admission prediction uses iris business process, integratedml and Java to import data, initialize the model, train the model, and then predict the results of the input conditions

![img.png](SourceCode%2Fdemo%2Fsrc%2Fmain%2Fresources%2Fstatic%2Fimage%2Fimg.png)
![img.png](SourceCode%2Fdemo%2Fimg.png)
![img_1.png](SourceCode%2Fdemo%2Fimg_1.png)
![img_2.png](SourceCode%2Fdemo%2Fimg_2.png)


### Operation steps

* 1. Import code in IRIS and compile it
![img_3.png](SourceCode%2Fdemo%2Fimg_3.png)
* 2. Start production
![img_4.png](SourceCode%2Fdemo%2Fimg_4.png)
* 3. Run the jar package (modify the configuration file)
1. Enter the correct IP port and namespace name
![img_5.png](SourceCode%2Fdemo%2Fimg_5.png)
![img_6.png](SourceCode%2Fdemo%2Fimg_6.png)
* 4. Access http://localhost:9097/swagger -Ui.html
![img_7.png](SourceCode%2Fdemo%2Fimg_7.png)