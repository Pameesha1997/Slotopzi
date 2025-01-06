import React from 'react'
import { Link } from 'react-router-dom'

export default function ButtonRedProps(props) {
    return (
        <div>
            <Link to={props.link}>
                <div className="w-24 h-10 rounded-lg bg-red-700 flex items-center justify-center" >
                    <h1 className="text-lg font-primary font-medium text-white">{props.name}</h1>
                </div>
            </Link>
        </div>
    )
}
