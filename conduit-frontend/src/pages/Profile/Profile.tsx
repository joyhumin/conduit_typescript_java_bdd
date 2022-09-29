import React from "react";
import { Typography } from "@mui/material";

interface Props {
  content: string;
}


export const Profile: React.FC<Props> = ({ content }) => {
  console.log(`*********Profile page rendered*********`);
  return (
    <>
        <Typography variant="h5" sx={ { flexGrow: 1 } }>{ `${content} profile page` }</Typography>
    </>
  );
};
