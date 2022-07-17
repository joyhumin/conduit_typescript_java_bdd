import React from "react";
import { create } from "react-test-renderer";
import { Typography } from "@mui/material";
import { Banner } from "../Banner";

jest.mock("@mui/material", () => mockAllReactComponents(jest.requireActual("@mui/material")));

describe("Banner", () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  test("renders without error", () => {
    // Given
    const headline = chance.string();
    const subheadline = chance.string();

    // When
    const actual = create(<Banner headline={headline} subheadline={subheadline} />).root;

    // Then
    expect(actual.props.headline).toEqual(headline);
    expect(actual.props.subheadline).toEqual(subheadline);
    const typographies = actual.findAllByType(Typography);
    expect(typographies[0].props["data-test"]).toEqual("headline");
    expect(typographies[1].props["data-test"]).toEqual("sub-headline");
  });
});
