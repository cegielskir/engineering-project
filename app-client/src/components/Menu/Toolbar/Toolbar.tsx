import React from 'react';

import ToggleButton from '../ToggleButton/ToggleButton';

import * as P from './parts';
import { Image } from 'assets/global-styles';
import logo from 'assets/img/logo-mini.png';

interface ToolbarProps {
    onToggleButtonClick: () => void;
}

const Toolbar: React.FC<ToolbarProps> = ({ onToggleButtonClick }) => (
    <P.Toolbar>
        <P.ToolbarNavigation>
            <P.ToolbarLogo>
                <a href="/">
                    <Image src={logo} />
                </a>
            </P.ToolbarLogo>
            <ToggleButton onClick={onToggleButtonClick} />
        </P.ToolbarNavigation>
    </P.Toolbar>
);

export default Toolbar;
