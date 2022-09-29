import React from "react";
import { Typography } from "@mui/material";

interface Props {
  content: string;
}


export const UserHomePage: React.FC<Props> = ({ content }) => {
  console.log("**********UserHomePage rendered**********");
  return (
    <>
        <Typography variant="h5" sx={ { flexGrow: 1 } }>{ content }</Typography>
    </>
  );
};
