import React, { useEffect, useRef } from "react";
import { Box, Container, Typography } from "@mui/material";
import { Link as RouterLink } from "react-router-dom";
import { useAuth, UserRegistration } from "../../hooks";
import { useNavigate } from "react-router";



export const Registration: React.FC = () => {
  console.log(`*********Registration rendered*********`);

  const [{ currentUser }, , registrationHandler] = useAuth();
  const navigate = useNavigate();
  // use formRef instead of setting each state to prevent unnecessary re-rendering for each keypress from user
  const formRef = useRef<HTMLFormElement>(null); // save any instance, useRef always create singleton object, always
  // has current

  function handleSubmit(event: React.FormEvent) {
    // if the event does not get explicitly handled, its default action should not be taken as it normally would be.
    // ref: https://developer.mozilla.org/en-US/docs/Web/API/Event/preventDefault
    event.preventDefault();
    if (formRef.current) {
      // ref: https://developer.mozilla.org/en-US/docs/Web/API/FormData
      const form = new FormData(formRef.current); // can make ajax call directly using form data :)
      // todo: hardcoded the new user request for now
      const submitNewUser: UserRegistration = {
        email: form.get("email") as string,
        password: form.get("password") as string,
        username: form.get("username") as string,
      };
      registrationHandler(submitNewUser);
    }
  }

  useEffect(() => {
    if (currentUser) {
      console.log("called navigation");
      navigate(`/`);
    }
  }, [currentUser]);


  return (
    <Container maxWidth={"sm"}>
      <Box
        component={"main"}
        display={"flex"}
        flexDirection={"column"}
        gap={"10px"}
        textAlign={"center"}
        paddingX={"15px"}>
        <Typography variant="h3">{"Sign Up"}</Typography>
        <Box component={RouterLink}
             to={"/login"}
             sx={{ textDecoration: "none", color: "text.secondary" }}>
          Already have an account?
        </Box>

        <form ref={formRef} onSubmit={handleSubmit}>
          <p>
            <label htmlFor="name">Name: </label>
            <input
              type="text"
              name="username"
              id="name"
              required={true}
              placeholder={"Enter your username"} />
          </p>
          <p>
            <label htmlFor="mail">E-mail: </label>
            <input
              type="email"
              name="email"
              id="mail"
              required={true}
              placeholder={"Enter your email"} />
          </p>
          <p>
            <label htmlFor={"pwd"}>Password: </label>
            <input
              type={"password"}
              name={"password"}
              id={"pwd"}
              required={true}
              placeholder={"Enter your password"} />
          </p>
          <p>
            <button type={"submit"}>Sign up</button>
          </p>
        </form>
      </Box>
    </Container>
  );
};
