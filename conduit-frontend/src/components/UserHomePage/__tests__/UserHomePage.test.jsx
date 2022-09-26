import React from "react";
import { create } from "react-test-renderer";
import { Typography } from "@mui/material";
import { UserHomePage } from "../UserHomePage";

jest.mock("react-helmet-async", () => ({
  Helmet: mockReactComponent(), // We must mock Helmet because it needs the HelmetProvider.
}));
jest.mock("@mui/material", () => ({
  Typography: mockReactComponent(),
}));
jest.mock("../../Layout");
jest.mock("../../../components/PageHeader", () => ({
  PageHeader: mockReactComponent(),
}));

describe("UserHomePage", () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  test("renders without error", () => {
    // Given
    const content = chance.string();

    // When
    const actual = create(<UserHomePage content={content} />).root;

    // Then
    expect(actual.findByType(Typography).props).toMatchObject({ variant: "h5", children: content });
  });
});
