import React from 'react';
import './DropdownList.css';

interface DropdownListProps {
    title: string;
    items: string[];
    chooseOption: (item: string) => void;
}

const DropdownList: React.FC<DropdownListProps> = (props) => {
    return (
        <nav>
            <ul>
                <li><a href="#">{props.title}<div id="down-triangle"></div></a>
                    <ul>
                        {
                            props.items.map((item, index) => {
                                return (<li key={index}>
                                    <a onClick={() => props.chooseOption(item)} >
                                        {item}<div className="circle"></div>
                                    </a>
                                </li>)
                            })
                        }
                    </ul>
                </li>
            </ul>
        </nav>
    );
};

export default DropdownList;

