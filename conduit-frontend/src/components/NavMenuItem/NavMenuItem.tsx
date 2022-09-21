import React from "react";
import { NavLink } from "react-router-dom";
import { Box } from "@mui/material";

interface Props {
  itemName: string;
  to: string;
}

export const NavMenuItem: React.FC<Props> = ({itemName, to}) => {
  return (
    <Box
      component={"li"}
      sx={{display: "inline-block"}}
      mx={1}
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
