import React from 'react';
import { Route, Routes } from "react-router-dom";
import { SignIn } from "./pages/SignIn";
import { Layout } from "./pages/Layout";
import { PublicHomePage } from "./pages/HomePage";
import { Registration } from "./pages/Registration";
import { Profile } from "./pages/Profile";

function App() {
  console.log("**********App rendered**********");
  return (
    <Routes>
      <Route path={"/"} element={<Layout />}>
        <Route index element={<PublicHomePage />} />
        <Route path={"login"} element={<SignIn content={"Sign in"} />} />
        <Route path={"register"} element={<Registration />} />
        <Route path={"profile/:username"} element={<Profile/>}/>
      </Route>
    </Routes>
  );
}

export default App;
