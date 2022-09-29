import React from "react";
import { Box, Typography } from "@mui/material";

interface Props {
  headline: string;
  subheadline: string;
}

export const Banner: React.FC<Props> = ({ headline, subheadline }) => {
  return <Box
    data-test={"banner"}
    sx={{backgroundColor: "primary.main"}}
    padding={"2rem"}
    mb={"2rem"}
    flexGrow={1}
    textAlign={"center"}
  >
    <Typography
      component={"h1"}
      fontSize={"3.5rem"} color={"white"} pb={"0.5rem"}
      data-test={"headline"}>
      {headline}
    </Typography>
    <Typography fontSize={"1.5rem"}
      color={"white"} data-test={"sub-headline"}>{subheadline}</Typography>
  </Box>;
};
