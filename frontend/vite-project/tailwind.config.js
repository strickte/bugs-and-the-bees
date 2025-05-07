/** @type {import('tailwindcss').Config} */
export default {
  content: ["./src/**/*.{js,jsx}"],
  //"./src/*.{html,js,jsx}" instead?
  //On it's own, this change has not worked
  //also seems fine as is in bugfest-club repo
  theme: {
    extend: {},
  },
  plugins: [],
};
