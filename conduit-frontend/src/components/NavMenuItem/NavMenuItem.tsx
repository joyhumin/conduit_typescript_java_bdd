import React from "react";
import { NavLink } from "react-router-dom";
import { Box } from "@mui/material";

interface Props {
  itemName: string;
  to: string;
  ariaLabel?: string;
}

export const NavMenuItem: React.FC<Props> = ({itemName, to, ariaLabel}) => {
  return (
    <Box
      component={"li"}
      sx={{display: "inline-block"}}
      mx={1}
      aria-label={ariaLabel}
    >
      <NavLink
        style={({isActive}) => {
          return {
            textDecoration: "none",
            color: isActive ? "#333333" : "#b2b2b2",
          };
        }}
        to={to}
      >{itemName}</NavLink>
    </Box>
  );
};
