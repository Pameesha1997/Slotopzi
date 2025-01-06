import React from 'react'

export default function SectionsCardsTechnisianInchage(props) {
    return (
        <div className="flex flex-col justify-center items-center font-primary font-bold">
            <div>Technician Incharge</div>
            <div className="font-primary font-medium mt-1">{props.technician}</div>

        </div>
    )
}
