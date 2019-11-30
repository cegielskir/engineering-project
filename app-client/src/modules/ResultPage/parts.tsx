import styled from 'styled-components';

export const colors = ['#C3432F','#353535','#99C24D','#F18F01', '#8A897C', '#048BA8'];

export const SuspiciousFragment = styled.i<{color: string}>`
    color: ${props => props.color};
`;

