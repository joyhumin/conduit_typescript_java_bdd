import React from "react";
import { AppBar, Box, Toolbar, Typography } from "@mui/material";
import { Link as RouterLink } from "react-router-dom";


export const AppHeader: React.FC = () => {
  return (
    <Box sx={{flexGrow: 1}}>
      <AppBar
        elevation={0}
        position={"sticky"}
        color={"transparent"}
      >
        <Toolbar>
          {/*textDecorattion to remove underline of the hyperlink*/}
          <Typography
            variant={"h5"}
            component={RouterLink}
            to={"/"}
            sx={{color: "#5CB85C", textDecoration: 'none'}}>CONDUIT
          </Typography>
        </Toolbar>
      </AppBar>
    </Box>);
};
