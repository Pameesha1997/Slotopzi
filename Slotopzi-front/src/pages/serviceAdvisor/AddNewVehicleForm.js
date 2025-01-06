import React, { useEffect, useState } from 'react'
import TopContainer from '../../components/Atoms/serviceStation/TopContainer'
import SideNav from '../../components/Moleculars/serviceAdvisor/sideNav'
import AdminTopBar from '../../components/Atoms/admin/AdminTopBar'
import { Field, Form, Formik } from 'formik'
import SearchBtnDetailsForm from '../../components/Atoms/SearchBtnDetailsForm'
import Button from '../../components/Atoms/Button'
import DetailFormBtn from '../../components/Atoms/DetailFormBtn'
import DetailFormUpdatebtn from '../../components/Atoms/DetailFormUpdatebtn'
import axios from 'axios'
import LandingSection6 from '../../components/Organs/LandingSection6'
import { toast, ToastContainer } from 'react-toastify'
import { getCookie } from '../../jsfunctions/cookies'
import AppointmentContainer from '../../components/Atoms/serviceStation/AppointmentContainer'
import { useHistory, useLocation } from 'react-router-dom/cjs/react-router-dom.min'

import BackTopBar from '../../components/Atoms/serviceStation/BackTopBar'

export default function AddNewVehicleForm() {
    const [vehicleDetails, setvehicleDetails] = useState();
    const [disable, setdisable] = useState(false);
    const [addOrUpdate, setaddOrUpdate] = useState('Add');
    const [addBtnStyle, setaddBtnStyle] = useState("w-36 h-12 mb-12 mt-4  bg-blue-600 rounded-md flex items-center justify-center hover:shadow-lg");
    const userId=getCookie('userId');
    const location = useLocation();
    console.log(location.state.update);
    console.log(location.state.contact);
    console.log("vin " + location.state.vin);
    console.log("vehicleNumber" +location.state.vehicleNumber);


    var config = {
        headers: {
            'Authorization': 'Bearer ' + getCookie('token'),
        }
    }
    //set single button to add or update
    useEffect(() => {
        if (location.state.update != null) {
            setaddOrUpdate("Update");
            setaddBtnStyle("w-36 h-12 mb-12 mt-4  bg-gradient-to-r from-red-400 to-yellow-500  text-lg font-bold text-white rounded-md flex items-center justify-center hover:shadow-lg ");
        } else {
            setaddOrUpdate("Add");
            setaddBtnStyle("w-36 h-12 mb-12 mt-4  bg-green-600  text-lg font-bold text-white  rounded-md flex items-center justify-center hover:shadow-lg");
        }
        if (location.state.vin != null) {
            //get and set vehicle details
            axios.get(`${process.env.REACT_APP_API_BASE_URL}/advisor/getvehicle/${location.state.vin}`, config)
                .then(function (response) {
                    console.log(response.data);
                    setvehicleDetails(response.data);
                    setdisable(true);

                })
        }else if(location.state.vehicleNumber != null){
            console.log("vehicleNumber 2");
            
            axios.get(`${process.env.REACT_APP_API_BASE_URL}/advisor/getvehicle/${location.state.vehicleNumber}`, config)
                .then(function (response) {
                    console.log(response.data);
                    setvehicleDetails(response.data);
                    toast.dark('🚫 Need to update details');
                    setdisable(false);

                })
                .catch(function(error){
                    console.log(error.response.data);
                    
                })
        } 
        else{
            setvehicleDetails({
                vin: '',
                vehicleNumber: '',
                engineNo: '',
                chassisNo: '',
                make: '',
                model: '',
                contactNo: location.state.contact
            })
            setdisable(false);
        }
    }, [location.state.update])

    const history = useHistory();
    return (

        <div className=" bg-Background-0 h-full pb-12 ">
            <div className="flex flex-row">
                <div className="">
                    <SideNav />
                </div>
                <div className="w-full flex flex-col xl:ml-40 ">
                    {/* <TopContainer heading1="Dashboard" heading2="Service Advisor" addnewbtntext="Add New"/> */}
                    {/* <AdminTopBar name="Vehicle Registration" roleName="Service Advisor" /> */}
                    <BackTopBar link="/serviceadvisor/detailsform" heading1="Vehicle Info" />
                    <div className="mt-16">
                        <Formik
                            enableReinitialize
                            initialValues={vehicleDetails}
                            onSubmit={async (values, { resetform }) => {
                                // console.log("on submit"+values); 
                                if (addOrUpdate == "Add") {
                                    await new Promise((r) => setTimeout(r, 500));
                                    axios.post(`${process.env.REACT_APP_API_BASE_URL}/advisor/vehicle/add new`, values, config)
                                        .then(function (response) {
                                            console.log(response.data);
                                            toast.success(response.data, { onClose: () => history.push('/serviceadvisor/detailsform') });
                                        })
                                } else if (addOrUpdate == "Update") {
                                    // console.log("Update back end");
                                    await new Promise((r) => setTimeout(r, 500));
                                    axios.put(`${process.env.REACT_APP_API_BASE_URL}/advisor/vehicle/update`, values, config)
                                        .then(function (response) {
                                            console.log(response.data);
                                            toast.success(response.data, {
                                                onClose: () => history.push({
                                                    pathname: '/serviceadvisor/section/selection',
                                                    state: {
                                                        vehicleNo: values.vehicleNumber,
                                                        vin: values.vin,
                                                        userId: userId
                                                    }
                                                })
                                            });
                                        })
            
                                }

                            }
                            }
                        >
                            <Form id="add-vehicle-form">
                                <div className="w-full h-18 mt-3">
                                    <div className="font-primary text-2xl flex items-center justify-center w-full mb-2 font-bold text-white tracking-wider ">{addOrUpdate} Vehicle</div>
                                    <div className="flex flex-col items-center  mt-12 mb-12">
                                        <div className="flex justify-center w-9/12">
                                            <div className="bg-white  rounded-lg py-10 w-10/12 grid grid-cols-2 place-items-center  mb-4">
                                                <div className="flex flex-col ">

                                                    <Field id="contactNo" name="contactNo" className="hidden" />

                                                    <label htmlFor="vin" className="font-primary  text-md font-semibold  ">VIN</label>
                                                    <Field id="vin" name="vin" placeholder="0938383830123" className=" ml-5 mt-2 rounded-lg shadow-lg w-60 h-10 pl-5" disabled={disable} required />

                                                    <label htmlFor="engineNo" className="font-primary  text-md font-semibold  mt-10">Engine No.</label>
                                                    <Field id="engineNo" name="engineNo" placeholder="engineNo" className=" ml-5 mt-2 rounded-lg shadow-lg w-60 h-10 pl-5" required />

                                                    <label htmlFor="model" className="font-primary  text-md font-semibold  mt-10">Vehicle Model</label>
                                                    <Field id="model" name="model" placeholder="model" className=" ml-5 mt-2 rounded-lg shadow-lg w-60 h-10 pl-5" disabled={disable} required />
                                                </div>
                                                <div className="flex flex-col ">
                                                    <label htmlFor="vehicleNumber" className="font-primary  text-md font-semibold  ">Reg No.</label>
                                                    <Field id="vehicleNumber" name="vehicleNumber" placeholder="vehicleNumber" className=" ml-5 rounded-lg shadow-lg w-60 h-10  mt-2 pl-5" disabled={disable} required />

                                                    <label htmlFor="chassisNo" className="font-primary  text-md font-semibold  mt-10">chassis No</label>
                                                    <Field id="chassisNo" name="chassisNo" placeholder="Jane" className=" ml-5 rounded-lg shadow-lg w-60 h-10  mt-2 pl-5" disabled={disable} required />

                                                    <label htmlFor="make" className="font-primary  text-md font-semibold  mt-10">Make</label>
                                                    <Field id="make" name="make" placeholder="make" className=" ml-5 mt-2 rounded-lg shadow-lg w-60 h-10 pl-5" disabled={disable} required />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="flex justify-center  items-center h-18 mt-2">
                                    {/* <div className="flex justify-end mr-20  items-center w-1/2">
                                    <DetailFormUpdatebtn txt="Update" />
                                </div> */}
                                    <div className="flex justify-center items-center">
                                        {/* <DetailFormBtn txt="Add New" /> */}
                                        <button className={addBtnStyle} type="submit">{addOrUpdate}</button>
                                    </div>
                                </div>
                            </Form>
                        </Formik>
                    </div>
                </div>
            </div>
            <ToastContainer
                position="bottom-right"
                autoClose={3000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
            />
        </div >
    )
}
