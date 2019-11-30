import React from 'react';
import * as P from './parts';

interface ToggleButtonProps {
    onClick: () => void;
}

const ToggleButton: React.FC<ToggleButtonProps> = (props) => (
    <P.ToggleButton onClick={props.onClick}>
        <P.ToggleButtonLine />
        <P.ToggleButtonLine />
        <P.ToggleButtonLine />
    </P.ToggleButton>
);

export default ToggleButton;
