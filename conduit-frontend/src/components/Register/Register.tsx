import React from "react";
import { Box } from "@mui/material";

interface Props {
  content: string;
}

export const Register: React.FC<Props> = ({ content }) => {
  return <Box>{ content }</Box>;
};
