/* This example requires Tailwind CSS v2.0+ */
import { Fragment, useState } from 'react'
import { Listbox, Transition } from '@headlessui/react'
// import { CheckIcon, SelectorIcon } from '@heroicons/react/solid'

const people = [
    {
        id: 1,
        name: 'No issue Found',
    },
    {
        id: 2,
        name: 'Amod Lakshan',
    },
    {
        id: 3,
        name: 'Dumidu Kasun',
    },
    {
        id: 4,
        name: 'Madhawa Dissanayake',
    },
    {
        id: 5,
        name: 'Madhawa Dissanayake',
    },
    {
        id: 6,
        name: 'Madhawa Dissanayake',
    },
]

function classNames(...classes) {
    return classes.filter(Boolean).join(' ')
}

export default function ReasonDropdown() {
    const [selected, setSelected] = useState(people[0])

    return (
        <Listbox value={selected} onChange={setSelected}>
            {({ open }) => (
                <>
                    <div className="mt-1 relative w-3/4">
                        <Listbox.Button className="relative w-full bg-white border border-gray-300 rounded-md shadow-sm pl-3 pr-10 py-2 text-left cursor-default focus:outline-none focus:ring-1 focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                            <span className="flex items-center">
                                <span className="ml-3 block truncate">{selected.name}</span>
                            </span>
                            <span className="ml-3 absolute inset-y-0 right-0 flex items-center pr-2 pointer-events-none">
                                {/* <SelectorIcon className="h-5 w-5 text-gray-400" aria-hidden="true" /> */}
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 text-gray-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 9l4-4 4 4m0 6l-4 4-4-4" />
                                </svg>
                            </span>
                        </Listbox.Button>

                        <Transition
                            show={open}
                            as={Fragment}
                            leave="transition ease-in duration-100"
                            leaveFrom="opacity-100"
                            leaveTo="opacity-0"
                        >
                            <Listbox.Options
                                static
                                className="absolute z-10 mt-1 w-full bg-white shadow-lg max-h-56 rounded-md py-1 text-base ring-1 ring-black ring-opacity-5 overflow-auto focus:outline-none sm:text-sm"
                            >
                                {people.map((person) => (
                                    <Listbox.Option
                                        key={person.id}
                                        className={({ active }) =>
                                            classNames(
                                                active ? 'text-white bg-indigo-600' : 'text-gray-900',
                                                'cursor-default select-none relative py-2 pl-3 pr-9'
                                            )
                                        }
                                        value={person}
                                    >
                                        {({ selected, active }) => (
                                            <>
                                                <div className="flex items-center">
                                                    <span
                                                        className={classNames(selected ? 'font-semibold' : 'font-normal', 'ml-3 block truncate')}
                                                    >
                                                        {person.name}
                                                    </span>
                                                </div>

                                                {selected ? (
                                                    <span
                                                        className={classNames(
                                                            active ? 'text-white' : 'text-indigo-600',
                                                            'absolute inset-y-0 right-0 flex items-center pr-4'
                                                        )}
                                                    >
                                                        {/* <CheckIcon className="h-5 w-5" aria-hidden="true" /> */}
                                                        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                                                        </svg>
                                                    </span>
                                                ) : null}
                                            </>
                                        )}
                                    </Listbox.Option>
                                ))}
                            </Listbox.Options>
                        </Transition>
                    </div>
                </>
            )}
        </Listbox>
    )
}
