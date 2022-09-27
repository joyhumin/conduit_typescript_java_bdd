import React from "react";
import { NavMenuItem } from "../NavMenuItem";
import { Box } from "@mui/material";
import { CustomNavMenu } from "../CustomNavMenu";
import { useUserContext } from "../../contexts";

export const NavMenu: React.FC = () => {
  console.log("**********NavMenu rendered**********");
  const { loading } = useUserContext(); // todo: consider lift loading logic up when deal with refresh
  if (loading) {
    return <></>;
  }
  return (
    <nav>
      <Box component={"ul"} sx={{ listStyleType: "none" }}>
        <NavMenuItem itemName={"Home"} to={"/"} />
        <CustomNavMenu />
      </Box>
    </nav>
  );
};
