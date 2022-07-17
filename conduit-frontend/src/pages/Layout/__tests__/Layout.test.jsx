import React from "react";
import { create } from "react-test-renderer";
import { Layout } from "../Layout";
import { AppHeader } from "../../../components/AppHeader";
import { Box } from "@mui/material";

// mock for mui component
jest.mock("@mui/material", () => ({
  Box: mockReactComponent(),
}));
// mock self-defined component
jest.mock("../../../components/AppHeader");

describe("Layout", () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  test("renders without error", () => {
    // Given
    const content = chance.string();

    // When
    const actual = create(<Layout>{content}</Layout>).root;

    // Then
    expect(actual.findByType(AppHeader)).toBeDefined();
    expect(actual.findByType(Box)).toBeDefined();
  });
});
