import React, { ReactNode } from "react";
import { AppHeader } from "../../components/AppHeader";
import { Box } from "@mui/material";

interface Props {
  children: ReactNode;
}

export const Layout: React.FC<Props> = (
  { children}) => {
  return (
    <>
      <AppHeader/>
      <Box component={"main"}>{children}</Box>
    </>
  );
};
