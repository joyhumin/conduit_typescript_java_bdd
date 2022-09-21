import React from "react";
import { create } from "react-test-renderer";
import { PublicHomePage } from "../PublicHomePage";
import { Banner } from "../../../components/Banner";

jest.mock("@mui/material", () => mockAllReactComponents(jest.requireActual("@mui/material")));

jest.mock("../../../components/Banner");

describe("HomePage", () => {
  afterEach(() => {
    jest.restoreAllMocks();
  });

  test("renders without error", () => {
    // Given

    // When
    const actual = create(<PublicHomePage />).root;

    // Then
    expect(actual.findByType(Banner)).toBeDefined();
  });
});
