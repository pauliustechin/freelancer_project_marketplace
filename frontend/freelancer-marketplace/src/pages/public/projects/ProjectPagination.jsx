import Pagination from "@mui/material/Pagination";
import Stack from "@mui/material/Stack";
import useProjectsStore from "../../../store/projectsStore";
import { useEffect } from "react";
import { useLocation, useNavigate } from "react-router";

export default function ProjectPagination({ page, setPage }) {
  
  const { totalPages } = useProjectsStore((state) => state);
  const location = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    if (page != null) {
      const params = new URLSearchParams(location.search);
      params.set("page", page - 1);
      navigate(`?${params.toString()}`);
    }
  }, [page, navigate, location.search]);

  return (
    <Stack spacing={2}>
      <Pagination
        count={totalPages}
        page={page || 1}
        onChange={(e, v) => setPage(v)}
        sx={{
          "& .MuiPaginationItem-root": {
            color: "#333",
          },
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
