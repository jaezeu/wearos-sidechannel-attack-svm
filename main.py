import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.metrics import accuracy_score

watchsetFE = pd.read_csv("D:/dataset/totalrightcomb.csv")

corrmat=watchsetFE.corr()
fig=plt.figure(figsize=(10,9))
sns.heatmap(corrmat,vmin=-1,vmax=1,square=True)
plt.show()

watchset5.shape

X=watchsetFE.drop('Button',axis=1)
y=watchsetFE['Button']

#Spilitting Training and Test Set
#Train set 80% and Test set 20%
from sklearn.model_selection import train_test_split  
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.20) 

#Scaling data via Standardization
from sklearn.preprocessing import StandardScaler

scaler = StandardScaler()

X_train = scaler.fit_transform(X_train)
X_test = scaler.transform(X_test)

print(X.shape)
y.shape

from sklearn.svm import SVC  
svclassifier = SVC(kernel='rbf', C=150, gamma = 0.25)
svclassifier.fit(X_train, y_train)

import pickle
filename = 'finalized_model.pkl'
pickle.dump(svclassifier, open(filename, 'wb')) 
  
y_pred = svclassifier.predict(X_test) 

L3 = y_test.tolist()
csv_columns = ['Label','Prediction']
d = {'Label': L3, 'Prediction': y_pred}
predictions = pd.DataFrame(data=d)
predictions.to_csv("C:/Users/jaz/Desktop/predictlabels11.csv")

from sklearn.metrics import accuracy_score
print (accuracy_score(y_test,y_pred))

from sklearn.metrics import classification_report, confusion_matrix  
print(confusion_matrix(y_test, y_pred))  
print(classification_report(y_test, y_pred))  

from sklearn.model_selection import cross_val_score
accuracies = cross_val_score(estimator = svclassifier, X=X_train, y = y_train, cv = 10)
accuracies.mean()
accuracies.std()

 from sklearn.model_selection import GridSearchCV
 parameters = [{'C' : [1,10,50,100,150,250,1000],'kernel' : ['linear']},
                {'C' : [1,10,50,100,150,250,1000],'kernel' : ['rbf'],'gamma' : [0.5,0.25,0.2,0.15,0.1]}]
 grid_search = GridSearchCV(estimator = svclassifier,
                            param_grid = parameters,
                            scoring = 'accuracy',
                            cv = 10,
                            n_jobs = -1)
 grid_search = grid_search.fit(X_train,y_train)
 best_accuracy = grid_search.best_score_
 best_parameters = grid_search.best_params_



