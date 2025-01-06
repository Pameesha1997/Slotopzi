import React, { useEffect, useState } from 'react'
import FormInput from '../components/Atoms/FormInput'
import SideImg from '../components/Atoms/SideImg'
import SignUpForm from '../components/Moleculars/SignUpForm'
import ButtonHover from '../components/Atoms/ButtonHover'
import ButtonSecondary from '../components/Atoms/ButtonSecondary'
import { Link } from 'react-router-dom'
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import AlertText from '../components/Atoms/AlertText'
import { useHistory } from 'react-router'

require('dotenv').config();

export default function Signup() {
    const history = useHistory();

    const [firstname, setfirstname] = useState('');
    const [username, setusername] = useState('');
    const [lastname, setlastname] = useState('');
    const [email, setemail] = useState('');
    const [contactno, setcontactno] = useState('');
    const [address, setaddress] = useState('');
    const [address2, setaddress2] = useState('');
    const [city, setcity] = useState('');
    const [password, setpassword] = useState('');
    const [rcpwd, setrcpwd] = useState('');
    const [profile_State, setprofile_State] = useState('1');
    const [passwordMatch, setpasswordMatch] = useState(false);
    const [disable, setDisable] = useState(true);
    const [visibility, setVisibility] = useState('invisible');
    const [fieldCheckVisibility, setFieldCheckVisibility] = useState('invisible');


    useEffect(() => {
        if (firstname == '' || lastname == '' || username == '' || email == '' || contactno == '' || password == '') {
            setDisable(true);
            setFieldCheckVisibility("visible");
        } else if (password !== rcpwd) {
            setVisibility("visible");
            setDisable(true);
        } else {
            setVisibility("invisible");
            setDisable(false);
            setFieldCheckVisibility("invisible");
        }
    }, [password, rcpwd, firstname, lastname, username, email, contactno]);
    //this array useState will only effect to this function

    var submit = () => {
        if (!/^((\+\d{11})|\d{10})$/.test(contactno)) {
            toast.error('❌ Invalid Contact Number');
            return false;
        }
        if (!/.{5}/.test(password)) {
            toast.error('❌ Password Should Contain at least 5 Characters');
            return false;
        }

        let finalAddress = address + ',' + address2;
        axios.post(`${process.env.REACT_APP_API_BASE_URL}/auth/signup`, {
            "firstName": firstname,
            "lastName": lastname,
            "userName": username,
            "email": email,
            "contactNo": contactno,
            "address": finalAddress,
            "city": city,
            "password": password,
            // "profile_state": "1"
        })
            .then(function (response) {
                // handle success
                console.log(response);
                // TODO make alert styles for success response
                toast.success('Signup Sucessful!!');
                toast.success('Please Log in to Continue!!', { onClose: () => history.push('/login') });

            })
            .catch(function (error) {
                // handle error
                toast.error('❌ ' + error.response.data);
                //alert(error.response.data);
            })
            .then(function () {
                // always executed

            });
    }

    return (

        <div className="md:flex w-screen">
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
            <div className="hidden md:block md:w-1/2 ">
                <SideImg img="/imgs/header2.jpg" />
            </div>
            <div className="bg-primary-0 w-screen h-screen md:w-1/2 ">
                <div className="bg-white absolute top-1/4 md:top-20 w-screen h-screen md:w-1/2 md:fixed" style={{ borderTopLeftRadius: '150px' }}>
                    <h1 className="font-primary text-center md:text-5xl text-4xl text-current font-semibold mt-7 mb-7">Sign Up</h1>
                    <form className="h-screen">
                        <div className="md:w-10/12 w-screen mx-auto flex flex-col items-center overflow-auto h-1/3 lg:h-1/2 shadow-md rounded-lg">
                            <SignUpForm firstname={firstname} onChangefirstname={setfirstname} lastname={lastname} onChangelastname={setlastname} username={username} onChangeusername={setusername}
                                email={email} onChangeemail={setemail} contactNo={contactno} onChangecontact={setcontactno} address={address} onChangeaddress={setaddress}
                                address2={address2} onChangeaddress2={setaddress2} city={city} onChangecity={setcity} password={password} onChangepassword={setpassword} rcpwd={rcpwd} onChangercpwd={setrcpwd} />
                        </div>
                        {/* alert style of password not matching.*/}
                        <AlertText text="Password does not match" visibility={visibility} />
                        {/* TODO Modify the styles and the msg */}
                        <AlertText text="All fields need to be filled !" visibility={fieldCheckVisibility} />
                        <div className="text-white mt-7 flex items-center justify-center">
                            <div className="m-4" >
                                <ButtonHover disableBtn={disable} txt="Sign Up" clickaction={submit} />
                            </div>
                            <div className="m-4" >
                                <Link to="login"><ButtonSecondary txt="Already a Customer" /></Link>
                            </div>
                        </div>
                    </form>
                    {/* <div className="text-center">
                        <h1 className="font-primary font-extralight text-sm">Already Have an Account <br /><span className="text-blue-800"> Sign in</span> </h1>
                    </div> */}

                </div>

            </div>
        </div>

    )

}
