import React from "react";
import { create } from "react-test-renderer";
import { HomePage } from "../HomePage";
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
    const actual = create(<HomePage />).root;

    // Then
    expect(actual.findByType(Banner)).toBeDefined();
  });
});
