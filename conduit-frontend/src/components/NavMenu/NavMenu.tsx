import React from "react";
import { NavMenuItem } from "../NavMenuItem";
import { Box } from "@mui/material";
import { CustomNavMenu } from "../CustomNavMenu";

export const NavMenu: React.FC = () => {
  console.log("**********NavMenu rendered**********");
  return (
    <Box component={"ul"} sx={{ listStyleType: "none" }}>
      <NavMenuItem itemName={"Home"} to={"/"} />
      <CustomNavMenu />
    </Box>
  );
};
