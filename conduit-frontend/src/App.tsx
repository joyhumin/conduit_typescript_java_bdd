import React from 'react';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { HomePage } from "./pages/HomePage";
import { Layout } from "./pages/Layout";

function App() {
  return (
    <Router>
      <Layout>
        <Routes>
          <Route path={"/"} element={<HomePage />}></Route>
        </Routes>
      </Layout>
    </Router>
  );
}

export default App;
