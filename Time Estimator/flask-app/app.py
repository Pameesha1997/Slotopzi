# Before executing the app Verify This:
# pip install flask scikit-learn joblib pandas matplotlib numpy --user
from flask import Flask, render_template, request,jsonify
import joblib
import numpy as np
from flask_cors import CORS

app = Flask(__name__, template_folder='web')
CORS(app)


@app.route('/')
def student():
    return render_template("home.html")

# This function predict the estimated time with the Regression Model
def ValuePredictorDifferentialIssue(to_predict_list):
    to_predict = np.array(to_predict_list).reshape(-1, 1)
    loaded_model = joblib.load('model.sav')
    result = loaded_model.predict(to_predict)
    return result[0]

def ValuePredictorOilLeak(to_predict_list):
    to_predict = np.array(to_predict_list).reshape(-1, 1)
    loaded_model = joblib.load('oil-leak-model.sav')
    result = loaded_model.predict(to_predict)
    return result[0]

def ValuePredictorChangeClutch(to_predict_list):
    to_predict = np.array(to_predict_list).reshape(-1, 1)
    loaded_model = joblib.load('change-clutch-model.sav')
    result = loaded_model.predict(to_predict)
    return result[0]

def ValuePredictorGasketIssue(to_predict_list):
    to_predict = np.array(to_predict_list).reshape(-1, 1)
    loaded_model = joblib.load('gasket-issue-model.sav')
    result = loaded_model.predict(to_predict)
    return result[0]

def ValuePredictorSuspensionChange(to_predict_list):
    to_predict = np.array(to_predict_list).reshape(-1, 1)
    loaded_model = joblib.load('suspension-change-model.sav')
    result = loaded_model.predict(to_predict)
    return result[0]

def ValuePredictorLightVehicle(to_predict_list):
    to_predict = np.array(to_predict_list).reshape(-1, 1)
    loaded_model = joblib.load('light-alignment-model.sav')
    result = loaded_model.predict(to_predict)
    return result[0]

def ValuePredictorHeavyVehicle(to_predict_list):
    to_predict = np.array(to_predict_list).reshape(-1, 1)
    loaded_model = joblib.load('heavy-alignment-model.sav')
    result = loaded_model.predict(to_predict)
    return result[0]*0.9

def ValuePredictorService1000(to_predict_list):
    to_predict = np.array(to_predict_list).reshape(-1, 1)
    loaded_model = joblib.load('service-1000-model.sav')
    result = loaded_model.predict(to_predict)
    return result[0]*5.5

def ValuePredictorService5000(to_predict_list):
    to_predict = np.array(to_predict_list).reshape(-1, 1)
    loaded_model = joblib.load('service-5000-model.sav')
    result = loaded_model.predict(to_predict)
    return result[0]*7

def ValuePredictorService10000(to_predict_list):
    to_predict = np.array(to_predict_list).reshape(-1, 1)
    loaded_model = joblib.load('service-10000-model.sav')
    result = loaded_model.predict(to_predict)
    return result[0]*8.8

def ValuePredictorService40000(to_predict_list):
    to_predict = np.array(to_predict_list).reshape(-1, 1)
    loaded_model = joblib.load('service-40000-model.sav')
    result = loaded_model.predict(to_predict)
    return result[0]*12.3

def ValuePredictorTuneUp(to_predict_list):
    to_predict = np.array(to_predict_list).reshape(-1, 1)
    loaded_model = joblib.load('tune-up-model.sav')
    result = loaded_model.predict(to_predict)
    return result[0]*2.5

def ValuePredictorEngineRepair(to_predict_list):
    to_predict = np.array(to_predict_list).reshape(-1, 1)
    loaded_model = joblib.load('engine-repair-model.sav')
    result = loaded_model.predict(to_predict)
    return result[0]*4

def ValuePredictorInteriorWash(to_predict_list):
    to_predict = np.array(to_predict_list).reshape(-1, 1)
    loaded_model = joblib.load('interior-wash-model.sav')
    result = loaded_model.predict(to_predict)
    return result[0]*0.9

def ValuePredictorBodyWash(to_predict_list):
    to_predict = np.array(to_predict_list).reshape(-1, 1)
    loaded_model = joblib.load('body-wash-model.sav')
    result = loaded_model.predict(to_predict)
    return result[0]*0.6

# This function listen to the front-end and output the value
@app.route('/', methods=['POST', 'GET'])
def result():
    if request.method == 'POST':
        to_predict_list = request.form.to_dict()
        print(request.form)
        to_predict_list = list(to_predict_list.values())
        to_predict_list = list(map(float, to_predict_list))
        result = round(float(ValuePredictorDifferentialIssue(to_predict_list)), 2)
        return render_template("home.html", result=result)

# Json service which enables to send the order value via json and output 
# estimated time in json format
# required input content-type: application/json value: {'order': 30}
@app.route('/app/',methods=['POST', 'GET'])
def jsonService():
    if request.method == 'POST':
        total = 0
        data = request.json
        print(request.get_json())
        print(data['order'])
        order_value = data['order']
        sub_cat_list = data['serviceEntries']
        for cateagory in sub_cat_list:
            match cateagory:
                case "Oil Leak": 
                    total = total + round(float(ValuePredictorOilLeak(order_value)), 2)
                case "Differensal Issue": 
                    total = total + round(float(ValuePredictorDifferentialIssue(order_value)), 2)
                case "Change Clutch": 
                    total = total + round(float(ValuePredictorChangeClutch(order_value)), 2)
                case "Gasket Issue": 
                    total = total + round(float(ValuePredictorGasketIssue(order_value)), 2)
                case "Light Vehicle": 
                    total = total + round(float(ValuePredictorLightVehicle(order_value)), 2)
                case "Heavy Vehicle": 
                     total = total + round(float(ValuePredictorHeavyVehicle(order_value)), 2)
                case "1000KM": 
                    total = total + round(float(ValuePredictorService1000(order_value)), 2)
                case "5000KM": 
                    total = total + round(float(ValuePredictorService5000(order_value)), 2)
                case "10000KM": 
                    total = total + round(float(ValuePredictorService10000(order_value)), 2)
                case "40000KM": 
                    total = total + round(float(ValuePredictorService40000(order_value)), 2)
                case "Engine Repair": 
                     total = total + round(float(ValuePredictorEngineRepair(order_value)), 2)
                case "Suspension Change": 
                    total = total + round(float(ValuePredictorSuspensionChange(order_value)), 2)
                case "Tune Up": 
                    total = total + round(float(ValuePredictorTuneUp(order_value)), 2)
                case "Body wash": 
                    total = total + round(float(ValuePredictorBodyWash(order_value)), 2)
                case "Interior Wash": 
                    total = total + round(float(ValuePredictorInteriorWash(order_value)), 2)

        
        # result = round(float(ValuePredictorOilLeak(order_value)), 2)
        response = jsonify({"time_estimated":total})
        response.headers.add("Access-Control-Allow-Origin", "*")
        response.headers.add("Access-Control-Allow-Headers", "*")
        response.headers.add("Access-Control-Allow-Methods", "*")
        return response

if __name__ == '__main__':
    app.run(debug=True)