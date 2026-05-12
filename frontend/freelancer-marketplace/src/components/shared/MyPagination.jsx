import Pagination from "@mui/material/Pagination";
import Stack from "@mui/material/Stack";
import useProjectsStore from "../../store/projectsStore";
import { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router";

export default function MyPagination() {

  const { totalPages } = useProjectsStore((state) => state);
  const [page, setPage] = useState(1);
  const location = useLocation();
  const navigate = useNavigate();

  const params = new URLSearchParams(location.search);
  params.set("page", page - 1);
  navigate(`?${params.toString()}`)

  return (
    <Stack spacing={2}>
      <Pagination
        count={totalPages}
        page={page}
        onChange={(e, v) => setPage(v)}
        sx={{
          "& .Mui-selected": {
            backgroundColor: "#33a3be",
            color: "white",
          },
          "& .Mui-selected:hover": {
            backgroundColor: "#33a3be",
          },
        }}
      />
    </Stack>
  );
}
