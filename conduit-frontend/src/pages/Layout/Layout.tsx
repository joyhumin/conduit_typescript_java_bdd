import React from "react";
import { Outlet } from "react-router";
import { AppBar, Box,Toolbar, Typography } from "@mui/material";
import { Link as RouterLink } from "react-router-dom";
import { NavMenu } from "../../components/NavMenu";

export function Layout(){
  return (
    <>
      <Box sx={{flexGrow: 1}}>
        <AppBar
          elevation={0}
          position={"sticky"}
          color={"transparent"}
        >
          <Toolbar sx={{justifyContent: "space-between"}}>
            {/*textDecorattion to remove underline of the hyperlink*/}
            <Typography
              variant={"h5"}
              component={RouterLink}
              to={"/"}
              sx={{color: "text.secondary", textDecoration: 'none'}}>CONDUIT
            </Typography>
            <NavMenu/>
          </Toolbar>

        </AppBar>
      </Box>

      <Outlet/>
    </>
  );
}
