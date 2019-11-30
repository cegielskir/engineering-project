import React from 'react';
import * as P from './parts';

interface BackdropProps {
    onClick: () => void;
}

const Backdrop: React.FC<BackdropProps> = ({ onClick }) => (
    <P.Backdrop onClick={onClick} />
);

export default Backdrop;
