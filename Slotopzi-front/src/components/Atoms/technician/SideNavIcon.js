import React from 'react'
import { Link } from 'react-router-dom/cjs/react-router-dom.min'
import Button from '@material-tailwind/react/Button';
import { deleteAllCookies, eraseCookie } from '../../../jsfunctions/cookies';
export default function sideNavIcon({ showSidebar, setShowSidebar }) {
    function clearCookies() {
        eraseCookie('username');
        eraseCookie('userId');
        eraseCookie('email');
        eraseCookie('token');
        eraseCookie('userType');
    }
    return (
        <div className="flex justify-start ">
            {/* <div className="bg-black shadow-lg w-40 h-screen"> */}
            <div className="xl:hidden ">
                <Button
                    color="transparent"
                    buttonType="link"
                    size="lg"
                    iconOnly
                    rounded
                    ripple="light"
                    onClick={() => setShowSidebar('left-0')}
                >
                    {/* <Icon name="menu" size="2xl" color="bg-forth-0" /> */}
                    <img src="https://img.icons8.com/material-rounded/48/000000/menu--v4.png" />
                </Button>
                <div
                    className={`absolute top-2 xl:hidden ${showSidebar === 'left-0' ? 'left-40' : '-left-40'
                        } z-50 transition-all duration-300`}
                >
                    <Button
                        color="transparent"
                        buttonType="link"
                        size="lg"
                        iconOnly
                        rounded
                        ripple="light"
                        onClick={() => setShowSidebar('-left-40')}
                    >
                        {/* <Icon name="close" size="2xl" color="red" /> */}
                        <img src="https://img.icons8.com/fluency/96/000000/close-window.png" />
                    </Button>
                </div>
            </div>

            <div className={`h-screen fixed  top-0 xl:left-0 ${showSidebar} flex items-center justify-center flex-row flex-nowrap overflow-y-auto bg-white z-200 shadow-lg w-40   z-10 py-4 px-6 transition-all duration-300 xl:overflow-hidden`}>
                {/* <div > */}
                <div className="relative mt-24 h-screen">
                    <Link to="/technician/dashboard">
                        <div className="flex flex-col items-center justify-center w-20 h-20 hover:shadow-lg shadow-lg m-10 rounded-lg">
                            <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8 text-primary-0" viewBox="0 0 20 20" fill="currentColor">
                                <path fill-rule="evenodd" d="M11.49 3.17c-.38-1.56-2.6-1.56-2.98 0a1.532 1.532 0 01-2.286.948c-1.372-.836-2.942.734-2.106 2.106.54.886.061 2.042-.947 2.287-1.561.379-1.561 2.6 0 2.978a1.532 1.532 0 01.947 2.287c-.836 1.372.734 2.942 2.106 2.106a1.532 1.532 0 012.287.947c.379 1.561 2.6 1.561 2.978 0a1.533 1.533 0 012.287-.947c1.372.836 2.942-.734 2.106-2.106a1.533 1.533 0 01.947-2.287c1.561-.379 1.561-2.6 0-2.978a1.532 1.532 0 01-.947-2.287c.836-1.372-.734-2.942-2.106-2.106a1.532 1.532 0 01-2.287-.947zM10 13a3 3 0 100-6 3 3 0 000 6z" clip-rule="evenodd" />
                            </svg>
                            <h1 className="text-forth-0 font-primary text-xs m-1 font-bold">Dashboard</h1>

                        </div>
                    </Link>
                    <Link to="/">
                        <div onClick={deleteAllCookies} className="flex flex-col items-center justify-center w-20 h-20 hover:shadow-lg m-10 absolute bottom-10 rounded-lg">
                            <svg xmlns="http://www.w3.org/2000/svg" class="h-8 w-8 text-gray-300 hover:text-primary-0" viewBox="0 0 20 20" fill="currentColor">
                                <path fill-rule="evenodd" d="M3 3a1 1 0 00-1 1v12a1 1 0 102 0V4a1 1 0 00-1-1zm10.293 9.293a1 1 0 001.414 1.414l3-3a1 1 0 000-1.414l-3-3a1 1 0 10-1.414 1.414L14.586 9H7a1 1 0 100 2h7.586l-1.293 1.293z" clip-rule="evenodd" />
                            </svg>
                            <h1 className=" font-primary text-xs m-1 font-bold text-gray-300">Logout</h1>

                        </div>
                    </Link>
                </div>
            </div>
        </div>
    )
}


