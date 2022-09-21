import React from "react";
import { NavMenuItem } from "../NavMenuItem";
import { Box } from "@mui/material";

export const NavMenu: React.FC = () => {
  return (
    <Box component={"ul"} sx={{listStyleType: "none"}}>
      <NavMenuItem itemName={"Home"} to={"/"} />
      <NavMenuItem itemName={"Sign in"} to={"login"} />
      <NavMenuItem itemName={"Sign up"} to={"register"} />
    </Box>);
};
