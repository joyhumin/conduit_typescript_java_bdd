import React from 'react';
import App from './App';
import { create } from "react-test-renderer";
import { Route, BrowserRouter as Router } from "react-router-dom";
import { HomePage } from "./pages/HomePage";

jest.mock("react-router-dom", () => ({
  BrowserRouter: mockReactComponent(),
  Routes: mockReactComponent(),
  Route: mockReactComponent()
}));

// shallow render children
jest.mock("./pages/HomePage");

describe("App", () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  test("Can render the App component", () => {
    // Given

    // When
    const actual = create(<App />).root;

    // Then
    expect(actual.findByType(Router)).toBeDefined();
    const routes = actual.findAllByType(Route);
    expect(routes[0].props.path).toEqual("/"); // only check value, not object reference
    expect(routes[0].props.element.type).toEqual(HomePage);

  });
});