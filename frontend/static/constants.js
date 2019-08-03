export const PriceRange = [
  {
    label: "10萬以下",
    minValue: "",
    maxValue: "10"
  },
  {
    label: "10 - 35萬",
    minValue: "10",
    maxValue: "35"
  },
  {
    label: "35 - 50萬",
    minValue: "35",
    maxValue: "50"
  },
  {
    label: "50 - 70萬",
    minValue: "50",
    maxValue: "70"
  },
  {
    label: "70 - 100萬",
    minValue: "70",
    maxValue: "100"
  },
  {
    label: "100萬以上",
    minValue: "100",
    maxValue: ""
  }
]

const NOW_YEAR = new Date().getFullYear();
export const YearRange = [
  {
    label: "一年內",
    minValue: NOW_YEAR -1,
    maxValue: ""
  },
  {
    label: "一至三年",
    minValue: NOW_YEAR -3,
    maxValue: NOW_YEAR -1
  },
  {
    label: "三至五年",
    minValue: NOW_YEAR -5,
    maxValue: NOW_YEAR -3
  },
  {
    label: "五至八年",
    minValue: NOW_YEAR -8,
    maxValue: NOW_YEAR -5
  },
  {
    label: "八至十年",
    minValue: NOW_YEAR -10,
    maxValue: NOW_YEAR -8
  },
  {
    label: "十年以上",
    minValue: "",
    maxValue: NOW_YEAR -10
  }
]

export const MileageRange = [
  {
    label: "1萬公里以下",
    minValue: "",
    maxValue: "1"
  },
  {
    label: "1 - 3萬公里",
    minValue: "1",
    maxValue: "3"
  },
  {
    label: "3 - 5萬公里",
    minValue: "3",
    maxValue: "5"
  },
  {
    label: "5 - 8萬公里",
    minValue: "5",
    maxValue: "8"
  },
  {
    label: "8 - 10萬公里",
    minValue: "8",
    maxValue: "10"
  },
  {
    label: "10萬公里以上",
    minValue: "10",
    maxValue: ""
  }
]

export const HOT_BRANDS =  [
  132,
  72,
  105,
  75,
  101,
  113,
  115,
  108,
  90,
  86,
  135,
  134,
  104
]
