import React, { useEffect, useRef } from "react";
import { Box, Container, Typography } from "@mui/material";
import { Link as RouterLink } from "react-router-dom";
import { useAuth, UserSignIn } from "../../hooks";
import { useNavigate } from "react-router";
import { FormLabelledInput } from "../../components/FormLabelledInput";


interface Props {
  content: string;
}

export const SignIn: React.FC<Props> = ({ content }) => {
  console.log(`*********SingIn rendered*********`);
  const [{ currentUser }, signIn] = useAuth();
  const formRef = useRef<HTMLFormElement>(null);
  const navigate = useNavigate();
  // todo: update to use react hook form
  function handleSubmit(event: React.FormEvent) {
    event.preventDefault();
    if (formRef.current) {
      const form = new FormData(formRef.current);
      const user = {
        email: form.get("email"),
        password: form.get("password")
      } as UserSignIn;
      signIn(user);
    }
  }

  useEffect(() => {
    if (currentUser) {
      // navigate(`/profile/${currentUser.username}`);
      navigate(`/`);
    }
  }, [currentUser, navigate]);

  return (
    <Container maxWidth={"sm"}>
      <Box
        display={"flex"}
        flexDirection={"column"}
        gap={"10px"}
        textAlign={"center"}
        paddingX={"15px"}>
        <Typography variant="h3">{content}</Typography>
        <Box component={RouterLink}
             to={"/register"}
             sx={{ textDecoration: "none", color: "text.secondary" }}>
          Need an account?
        </Box>

        <form ref={formRef} onSubmit={handleSubmit}>
          <FormLabelledInput type={"email"} name={"email"} label={"E-mail"}/>
          <FormLabelledInput type={"password"} name={"password"} label={"Password"}/>
          <button type={"submit"} data-test={"sign-in-button"}>Sign in</button>
        </form>
      </Box>
    </Container>
  );
};
