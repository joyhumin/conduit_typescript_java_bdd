import React from "react";
import { AppBar, Toolbar, Typography } from "@mui/material";


export const AppHeader: React.FC = () => {
  return (<>
    <AppBar
      elevation={0}
      position={"sticky"}
      color={"transparent"}
    >
      <Toolbar>
        {/*logo*/}
        <Typography
          variant={"h5"}
          sx={{color: "#5CB85C"}}>CONDUIT</Typography>
        {/* navigation items*/}
      </Toolbar>
    </AppBar>
  </>);
};
